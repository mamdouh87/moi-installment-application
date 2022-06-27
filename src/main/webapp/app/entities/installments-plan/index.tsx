import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InstallmentsPlan from './installments-plan';
import InstallmentsPlanDetail from './installments-plan-detail';
import InstallmentsPlanUpdate from './installments-plan-update';
import InstallmentsPlanDeleteDialog from './installments-plan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InstallmentsPlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InstallmentsPlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InstallmentsPlanDetail} />
      <ErrorBoundaryRoute path={match.url} component={InstallmentsPlan} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InstallmentsPlanDeleteDialog} />
  </>
);

export default Routes;
