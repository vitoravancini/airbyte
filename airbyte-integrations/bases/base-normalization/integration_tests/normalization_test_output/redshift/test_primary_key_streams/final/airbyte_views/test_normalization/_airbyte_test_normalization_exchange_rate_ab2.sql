

  create view "integrationtests"._airbyte_test_normalization."_airbyte_test_normalization_exchange_rate_ab2__dbt_tmp" as (
    
-- SQL model to cast each column to its adequate SQL type converted from the JSON schema type
select
    cast(id as 
    bigint
) as id,
    cast(currency as varchar) as currency,
    cast(date as varchar) as date,
    cast(hkd as 
    float
) as hkd,
    cast(nzd as 
    float
) as nzd,
    cast(usd as 
    float
) as usd,
    _airbyte_emitted_at
from "integrationtests"._airbyte_test_normalization."exchange_rate_ab1"
-- exchange_rate
  ) ;
