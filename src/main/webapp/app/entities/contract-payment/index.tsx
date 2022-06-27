import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractPayment from './contract-payment';
import ContractPaymentDetail from './contract-payment-detail';
import ContractPaymentUpdate from './contract-payment-update';
import ContractPaymentDeleteDialog from './contract-payment-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractPaymentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractPaymentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractPaymentDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractPayment} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractPaymentDeleteDialog} />
  </>
);

export default Routes;
