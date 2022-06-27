import customer from 'app/entities/customer/customer.reducer';
import country from 'app/entities/country/country.reducer';
import request from 'app/entities/request/request.reducer';
import requestSystemFee from 'app/entities/request-system-fee/request-system-fee.reducer';
import requestSystemFeeDetails from 'app/entities/request-system-fee-details/request-system-fee-details.reducer';
import contract from 'app/entities/contract/contract.reducer';
import trafficContract from 'app/entities/traffic-contract/traffic-contract.reducer';
import contractSystemFee from 'app/entities/contract-system-fee/contract-system-fee.reducer';
import contractSystemFeeDetails from 'app/entities/contract-system-fee-details/contract-system-fee-details.reducer';
import contractPayment from 'app/entities/contract-payment/contract-payment.reducer';
import installmentsPlan from 'app/entities/installments-plan/installments-plan.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  customer,
  country,
  request,
  requestSystemFee,
  requestSystemFeeDetails,
  contract,
  trafficContract,
  contractSystemFee,
  contractSystemFeeDetails,
  contractPayment,
  installmentsPlan,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
