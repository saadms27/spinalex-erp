import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './py-situat.reducer';
import { IPySituat } from 'app/shared/model/py-situat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPySituatDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PySituatDetail extends React.Component<IPySituatDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pySituatEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PySituat [<b>{pySituatEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sitCd">Sit Cd</span>
            </dt>
            <dd>{pySituatEntity.sitCd}</dd>
            <dt>
              <span id="sitNm">Sit Nm</span>
            </dt>
            <dd>{pySituatEntity.sitNm}</dd>
          </dl>
          <Button tag={Link} to="/entity/py-situat" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/py-situat/${pySituatEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pySituat }: IRootState) => ({
  pySituatEntity: pySituat.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PySituatDetail);
