import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PyUnitCd from './py-unit-cd';
import PyUnitCdDetail from './py-unit-cd-detail';
import PyUnitCdUpdate from './py-unit-cd-update';
import PyUnitCdDeleteDialog from './py-unit-cd-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PyUnitCdUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PyUnitCdUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PyUnitCdDetail} />
      <ErrorBoundaryRoute path={match.url} component={PyUnitCd} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PyUnitCdDeleteDialog} />
  </>
);

export default Routes;
