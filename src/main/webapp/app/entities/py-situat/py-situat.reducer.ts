import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPySituat, defaultValue } from 'app/shared/model/py-situat.model';

export const ACTION_TYPES = {
  FETCH_PYSITUAT_LIST: 'pySituat/FETCH_PYSITUAT_LIST',
  FETCH_PYSITUAT: 'pySituat/FETCH_PYSITUAT',
  CREATE_PYSITUAT: 'pySituat/CREATE_PYSITUAT',
  UPDATE_PYSITUAT: 'pySituat/UPDATE_PYSITUAT',
  DELETE_PYSITUAT: 'pySituat/DELETE_PYSITUAT',
  RESET: 'pySituat/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPySituat>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PySituatState = Readonly<typeof initialState>;

// Reducer

export default (state: PySituatState = initialState, action): PySituatState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PYSITUAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PYSITUAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PYSITUAT):
    case REQUEST(ACTION_TYPES.UPDATE_PYSITUAT):
    case REQUEST(ACTION_TYPES.DELETE_PYSITUAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PYSITUAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PYSITUAT):
    case FAILURE(ACTION_TYPES.CREATE_PYSITUAT):
    case FAILURE(ACTION_TYPES.UPDATE_PYSITUAT):
    case FAILURE(ACTION_TYPES.DELETE_PYSITUAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PYSITUAT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PYSITUAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PYSITUAT):
    case SUCCESS(ACTION_TYPES.UPDATE_PYSITUAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PYSITUAT):
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

const apiUrl = 'api/py-situats';

// Actions

export const getEntities: ICrudGetAllAction<IPySituat> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PYSITUAT_LIST,
  payload: axios.get<IPySituat>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPySituat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PYSITUAT,
    payload: axios.get<IPySituat>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPySituat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PYSITUAT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPySituat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PYSITUAT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPySituat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PYSITUAT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
