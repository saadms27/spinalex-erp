import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './py-unit-cd.reducer';
import { IPyUnitCd } from 'app/shared/model/py-unit-cd.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPyUnitCdDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PyUnitCdDetail extends React.Component<IPyUnitCdDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pyUnitCdEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PyUnitCd [<b>{pyUnitCdEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="uCd">U Cd</span>
            </dt>
            <dd>{pyUnitCdEntity.uCd}</dd>
            <dt>
              <span id="uNm">U Nm</span>
            </dt>
            <dd>{pyUnitCdEntity.uNm}</dd>
          </dl>
          <Button tag={Link} to="/entity/py-unit-cd" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/py-unit-cd/${pyUnitCdEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pyUnitCd }: IRootState) => ({
  pyUnitCdEntity: pyUnitCd.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PyUnitCdDetail);
