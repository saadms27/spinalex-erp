import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PySituat from './py-situat';
import PySituatDetail from './py-situat-detail';
import PySituatUpdate from './py-situat-update';
import PySituatDeleteDialog from './py-situat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PySituatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PySituatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PySituatDetail} />
      <ErrorBoundaryRoute path={match.url} component={PySituat} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PySituatDeleteDialog} />
  </>
);

export default Routes;
