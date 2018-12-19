import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PyPersDa from './py-pers-da';
import PyUnitCd from './py-unit-cd';
import PySituat from './py-situat';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/py-pers-da`} component={PyPersDa} />
      <ErrorBoundaryRoute path={`${match.url}/py-unit-cd`} component={PyUnitCd} />
      <ErrorBoundaryRoute path={`${match.url}/py-situat`} component={PySituat} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
