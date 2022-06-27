import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/customer">
        <Translate contentKey="global.menu.entities.customer" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/country">
        <Translate contentKey="global.menu.entities.country" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/request">
        <Translate contentKey="global.menu.entities.request" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/request-system-fee">
        <Translate contentKey="global.menu.entities.requestSystemFee" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/request-system-fee-details">
        <Translate contentKey="global.menu.entities.requestSystemFeeDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract">
        <Translate contentKey="global.menu.entities.contract" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/traffic-contract">
        <Translate contentKey="global.menu.entities.trafficContract" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract-system-fee">
        <Translate contentKey="global.menu.entities.contractSystemFee" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract-system-fee-details">
        <Translate contentKey="global.menu.entities.contractSystemFeeDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract-payment">
        <Translate contentKey="global.menu.entities.contractPayment" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/installments-plan">
        <Translate contentKey="global.menu.entities.installmentsPlan" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu as React.ComponentType<any>;
