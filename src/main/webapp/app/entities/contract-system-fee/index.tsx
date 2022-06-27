import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractSystemFee from './contract-system-fee';
import ContractSystemFeeDetail from './contract-system-fee-detail';
import ContractSystemFeeUpdate from './contract-system-fee-update';
import ContractSystemFeeDeleteDialog from './contract-system-fee-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractSystemFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractSystemFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractSystemFeeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractSystemFee} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractSystemFeeDeleteDialog} />
  </>
);

export default Routes;
