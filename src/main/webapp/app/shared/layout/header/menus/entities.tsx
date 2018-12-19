import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <DropdownItem tag={Link} to="/entity/py-pers-da">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Py Pers Da
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/py-unit-cd">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Py Unit Cd
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/py-situat">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Py Situat
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
