import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './py-unit-cd.reducer';
import { IPyUnitCd } from 'app/shared/model/py-unit-cd.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPyUnitCdProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PyUnitCd extends React.Component<IPyUnitCdProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { pyUnitCdList, match } = this.props;
    return (
      <div>
        <h2 id="py-unit-cd-heading">
          Py Unit Cds
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Py Unit Cd
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>U Cd</th>
                <th>U Nm</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pyUnitCdList.map((pyUnitCd, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pyUnitCd.id}`} color="link" size="sm">
                      {pyUnitCd.id}
                    </Button>
                  </td>
                  <td>{pyUnitCd.uCd}</td>
                  <td>{pyUnitCd.uNm}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pyUnitCd.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pyUnitCd.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pyUnitCd.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ pyUnitCd }: IRootState) => ({
  pyUnitCdList: pyUnitCd.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PyUnitCd);
