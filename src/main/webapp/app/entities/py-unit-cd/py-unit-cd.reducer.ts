import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPyUnitCd, defaultValue } from 'app/shared/model/py-unit-cd.model';

export const ACTION_TYPES = {
  FETCH_PYUNITCD_LIST: 'pyUnitCd/FETCH_PYUNITCD_LIST',
  FETCH_PYUNITCD: 'pyUnitCd/FETCH_PYUNITCD',
  CREATE_PYUNITCD: 'pyUnitCd/CREATE_PYUNITCD',
  UPDATE_PYUNITCD: 'pyUnitCd/UPDATE_PYUNITCD',
  DELETE_PYUNITCD: 'pyUnitCd/DELETE_PYUNITCD',
  RESET: 'pyUnitCd/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPyUnitCd>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PyUnitCdState = Readonly<typeof initialState>;

// Reducer

export default (state: PyUnitCdState = initialState, action): PyUnitCdState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PYUNITCD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PYUNITCD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PYUNITCD):
    case REQUEST(ACTION_TYPES.UPDATE_PYUNITCD):
    case REQUEST(ACTION_TYPES.DELETE_PYUNITCD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PYUNITCD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PYUNITCD):
    case FAILURE(ACTION_TYPES.CREATE_PYUNITCD):
    case FAILURE(ACTION_TYPES.UPDATE_PYUNITCD):
    case FAILURE(ACTION_TYPES.DELETE_PYUNITCD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PYUNITCD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PYUNITCD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PYUNITCD):
    case SUCCESS(ACTION_TYPES.UPDATE_PYUNITCD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PYUNITCD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/py-unit-cds';

// Actions

export const getEntities: ICrudGetAllAction<IPyUnitCd> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PYUNITCD_LIST,
  payload: axios.get<IPyUnitCd>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPyUnitCd> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PYUNITCD,
    payload: axios.get<IPyUnitCd>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPyUnitCd> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PYUNITCD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPyUnitCd> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PYUNITCD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPyUnitCd> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PYUNITCD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
