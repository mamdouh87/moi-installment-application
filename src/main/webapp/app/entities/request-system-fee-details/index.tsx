import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RequestSystemFeeDetails from './request-system-fee-details';
import RequestSystemFeeDetailsDetail from './request-system-fee-details-detail';
import RequestSystemFeeDetailsUpdate from './request-system-fee-details-update';
import RequestSystemFeeDetailsDeleteDialog from './request-system-fee-details-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RequestSystemFeeDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RequestSystemFeeDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RequestSystemFeeDetailsDetail} />
      <ErrorBoundaryRoute path={match.url} component={RequestSystemFeeDetails} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RequestSystemFeeDetailsDeleteDialog} />
  </>
);

export default Routes;
