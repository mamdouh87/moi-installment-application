import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TrafficContract from './traffic-contract';
import TrafficContractDetail from './traffic-contract-detail';
import TrafficContractUpdate from './traffic-contract-update';
import TrafficContractDeleteDialog from './traffic-contract-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TrafficContractUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TrafficContractUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TrafficContractDetail} />
      <ErrorBoundaryRoute path={match.url} component={TrafficContract} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TrafficContractDeleteDialog} />
  </>
);

export default Routes;
