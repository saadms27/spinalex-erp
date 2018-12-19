import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPyPersDa } from 'app/shared/model/py-pers-da.model';
import { getEntities as getPyPersDas } from 'app/entities/py-pers-da/py-pers-da.reducer';
import { getEntity, updateEntity, createEntity, reset } from './py-unit-cd.reducer';
import { IPyUnitCd } from 'app/shared/model/py-unit-cd.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPyUnitCdUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPyUnitCdUpdateState {
  isNew: boolean;
  uCdId: string;
}

export class PyUnitCdUpdate extends React.Component<IPyUnitCdUpdateProps, IPyUnitCdUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      uCdId: '0',
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

    this.props.getPyPersDas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { pyUnitCdEntity } = this.props;
      const entity = {
        ...pyUnitCdEntity,
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
    this.props.history.push('/entity/py-unit-cd');
  };

  render() {
    const { pyUnitCdEntity, pyPersDas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="spinalexerpApp.pyUnitCd.home.createOrEditLabel">Create or edit a PyUnitCd</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pyUnitCdEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="py-unit-cd-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="uCdLabel" for="uCd">
                    U Cd
                  </Label>
                  <AvField id="py-unit-cd-uCd" type="string" className="form-control" name="uCd" />
                </AvGroup>
                <AvGroup>
                  <Label id="uNmLabel" for="uNm">
                    U Nm
                  </Label>
                  <AvField id="py-unit-cd-uNm" type="text" name="uNm" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/py-unit-cd" replace color="info">
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
  pyPersDas: storeState.pyPersDa.entities,
  pyUnitCdEntity: storeState.pyUnitCd.entity,
  loading: storeState.pyUnitCd.loading,
  updating: storeState.pyUnitCd.updating,
  updateSuccess: storeState.pyUnitCd.updateSuccess
});

const mapDispatchToProps = {
  getPyPersDas,
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
)(PyUnitCdUpdate);
