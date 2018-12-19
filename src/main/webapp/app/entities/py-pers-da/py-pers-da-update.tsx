import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPyUnitCd } from 'app/shared/model/py-unit-cd.model';
import { getEntities as getPyUnitCds } from 'app/entities/py-unit-cd/py-unit-cd.reducer';
import { IPySituat } from 'app/shared/model/py-situat.model';
import { getEntities as getPySituats } from 'app/entities/py-situat/py-situat.reducer';
import { getEntity, updateEntity, createEntity, reset } from './py-pers-da.reducer';
import { IPyPersDa } from 'app/shared/model/py-pers-da.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPyPersDaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPyPersDaUpdateState {
  isNew: boolean;
  uUCdId: string;
  sitCdId: string;
}

export class PyPersDaUpdate extends React.Component<IPyPersDaUpdateProps, IPyPersDaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      uUCdId: '0',
      sitCdId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getPyUnitCds();
    this.props.getPySituats();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { pyPersDaEntity } = this.props;
      const entity = {
        ...pyPersDaEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/py-pers-da');
  };

  render() {
    const { pyPersDaEntity, pyUnitCds, pySituats, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="spinalexerpApp.pyPersDa.home.createOrEditLabel">Create or edit a PyPersDa</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pyPersDaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="py-pers-da-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="empNoLabel" for="empNo">
                    Emp No
                  </Label>
                  <AvField id="py-pers-da-empNo" type="text" name="empNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="uUCdLabel" for="uUCd">
                    U U Cd
                  </Label>
                  <AvField id="py-pers-da-uUCd" type="string" className="form-control" name="uUCd" />
                </AvGroup>
                <AvGroup>
                  <Label id="sitCdLabel" for="sitCd">
                    Sit Cd
                  </Label>
                  <AvField id="py-pers-da-sitCd" type="string" className="form-control" name="sitCd" />
                </AvGroup>
                <AvGroup>
                  <Label for="uUCd.id">U U Cd</Label>
                  <AvInput id="py-pers-da-uUCd" type="select" className="form-control" name="uUCd.id">
                    <option value="" key="0" />
                    {pyUnitCds
                      ? pyUnitCds.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="sitCd.id">Sit Cd</Label>
                  <AvInput id="py-pers-da-sitCd" type="select" className="form-control" name="sitCd.id">
                    <option value="" key="0" />
                    {pySituats
                      ? pySituats.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/py-pers-da" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  pyUnitCds: storeState.pyUnitCd.entities,
  pySituats: storeState.pySituat.entities,
  pyPersDaEntity: storeState.pyPersDa.entity,
  loading: storeState.pyPersDa.loading,
  updating: storeState.pyPersDa.updating,
  updateSuccess: storeState.pyPersDa.updateSuccess
});

const mapDispatchToProps = {
  getPyUnitCds,
  getPySituats,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PyPersDaUpdate);
