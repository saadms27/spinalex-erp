import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PyPersDa from './py-pers-da';
import PyPersDaDetail from './py-pers-da-detail';
import PyPersDaUpdate from './py-pers-da-update';
import PyPersDaDeleteDialog from './py-pers-da-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PyPersDaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PyPersDaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PyPersDaDetail} />
      <ErrorBoundaryRoute path={match.url} component={PyPersDa} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PyPersDaDeleteDialog} />
  </>
);

export default Routes;
