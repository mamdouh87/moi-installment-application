import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractSystemFeeDetails from './contract-system-fee-details';
import ContractSystemFeeDetailsDetail from './contract-system-fee-details-detail';
import ContractSystemFeeDetailsUpdate from './contract-system-fee-details-update';
import ContractSystemFeeDetailsDeleteDialog from './contract-system-fee-details-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractSystemFeeDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractSystemFeeDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractSystemFeeDetailsDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractSystemFeeDetails} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractSystemFeeDetailsDeleteDialog} />
  </>
);

export default Routes;
