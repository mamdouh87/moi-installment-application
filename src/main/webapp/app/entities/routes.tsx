import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Customer from './customer';
import Country from './country';
import Request from './request';
import RequestSystemFee from './request-system-fee';
import RequestSystemFeeDetails from './request-system-fee-details';
import Contract from './contract';
import TrafficContract from './traffic-contract';
import ContractSystemFee from './contract-system-fee';
import ContractSystemFeeDetails from './contract-system-fee-details';
import ContractPayment from './contract-payment';
import InstallmentsPlan from './installments-plan';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}customer`} component={Customer} />
        <ErrorBoundaryRoute path={`${match.url}country`} component={Country} />
        <ErrorBoundaryRoute path={`${match.url}request`} component={Request} />
        <ErrorBoundaryRoute path={`${match.url}request-system-fee`} component={RequestSystemFee} />
        <ErrorBoundaryRoute path={`${match.url}request-system-fee-details`} component={RequestSystemFeeDetails} />
        <ErrorBoundaryRoute path={`${match.url}contract`} component={Contract} />
        <ErrorBoundaryRoute path={`${match.url}traffic-contract`} component={TrafficContract} />
        <ErrorBoundaryRoute path={`${match.url}contract-system-fee`} component={ContractSystemFee} />
        <ErrorBoundaryRoute path={`${match.url}contract-system-fee-details`} component={ContractSystemFeeDetails} />
        <ErrorBoundaryRoute path={`${match.url}contract-payment`} component={ContractPayment} />
        <ErrorBoundaryRoute path={`${match.url}installments-plan`} component={InstallmentsPlan} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
