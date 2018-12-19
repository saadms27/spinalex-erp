import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './py-pers-da.reducer';
import { IPyPersDa } from 'app/shared/model/py-pers-da.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPyPersDaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PyPersDaDetail extends React.Component<IPyPersDaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pyPersDaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PyPersDa [<b>{pyPersDaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="empNo">Emp No</span>
            </dt>
            <dd>{pyPersDaEntity.empNo}</dd>
            <dt>
              <span id="uUCd">U U Cd</span>
            </dt>
            <dd>{pyPersDaEntity.uUCd}</dd>
            <dt>
              <span id="sitCd">Sit Cd</span>
            </dt>
            <dd>{pyPersDaEntity.sitCd}</dd>
            <dt>U U Cd</dt>
            <dd>{pyPersDaEntity.uUCd ? pyPersDaEntity.uUCd.id : ''}</dd>
            <dt>Sit Cd</dt>
            <dd>{pyPersDaEntity.sitCd ? pyPersDaEntity.sitCd.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/py-pers-da" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/py-pers-da/${pyPersDaEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pyPersDa }: IRootState) => ({
  pyPersDaEntity: pyPersDa.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PyPersDaDetail);
