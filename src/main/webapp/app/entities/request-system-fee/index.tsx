import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RequestSystemFee from './request-system-fee';
import RequestSystemFeeDetail from './request-system-fee-detail';
import RequestSystemFeeUpdate from './request-system-fee-update';
import RequestSystemFeeDeleteDialog from './request-system-fee-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RequestSystemFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RequestSystemFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RequestSystemFeeDetail} />
      <ErrorBoundaryRoute path={match.url} component={RequestSystemFee} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RequestSystemFeeDeleteDialog} />
  </>
);

export default Routes;
