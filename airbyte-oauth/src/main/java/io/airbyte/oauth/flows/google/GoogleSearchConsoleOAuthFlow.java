/*
 * Copyright (c) 2021 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.oauth.flows.google;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.airbyte.config.persistence.ConfigRepository;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.function.Supplier;

public class GoogleSearchConsoleOAuthFlow extends GoogleOAuthFlow {

  @VisibleForTesting
  static final String SCOPE_URL = "https://www.googleapis.com/auth/webmasters.readonly";

  public GoogleSearchConsoleOAuthFlow(final ConfigRepository configRepository) {
    super(configRepository);
  }

  @VisibleForTesting
  GoogleSearchConsoleOAuthFlow(final ConfigRepository configRepository, final HttpClient httpClient, final Supplier<String> stateSupplier) {
    super(configRepository, httpClient, stateSupplier);
  }

  @Override
  protected String getScope() {
    return SCOPE_URL;
  }

  @Override
  protected String getClientIdUnsafe(final JsonNode config) {
    // the config object containing client ID and secret is nested inside the "authorization" object
    Preconditions.checkArgument(config.hasNonNull("authorization"));
    return super.getClientIdUnsafe(config.get("authorization"));
  }

  @Override
  protected String getClientSecretUnsafe(final JsonNode config) {
    // the config object containing client ID and secret is nested inside the "authorization" object
    Preconditions.checkArgument(config.hasNonNull("authorization"));
    return super.getClientSecretUnsafe(config.get("authorization"));
  }

  @Override
  protected Map<String, Object> extractRefreshToken(final JsonNode data) throws IOException {
    // the config object containing refresh token is nested inside the "authorization" object
    return Map.of("authorization", super.extractRefreshToken(data));
  }

}
