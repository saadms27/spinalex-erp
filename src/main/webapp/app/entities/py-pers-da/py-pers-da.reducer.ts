import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPyPersDa, defaultValue } from 'app/shared/model/py-pers-da.model';

export const ACTION_TYPES = {
  FETCH_PYPERSDA_LIST: 'pyPersDa/FETCH_PYPERSDA_LIST',
  FETCH_PYPERSDA: 'pyPersDa/FETCH_PYPERSDA',
  CREATE_PYPERSDA: 'pyPersDa/CREATE_PYPERSDA',
  UPDATE_PYPERSDA: 'pyPersDa/UPDATE_PYPERSDA',
  DELETE_PYPERSDA: 'pyPersDa/DELETE_PYPERSDA',
  RESET: 'pyPersDa/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPyPersDa>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PyPersDaState = Readonly<typeof initialState>;

// Reducer

export default (state: PyPersDaState = initialState, action): PyPersDaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PYPERSDA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PYPERSDA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PYPERSDA):
    case REQUEST(ACTION_TYPES.UPDATE_PYPERSDA):
    case REQUEST(ACTION_TYPES.DELETE_PYPERSDA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PYPERSDA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PYPERSDA):
    case FAILURE(ACTION_TYPES.CREATE_PYPERSDA):
    case FAILURE(ACTION_TYPES.UPDATE_PYPERSDA):
    case FAILURE(ACTION_TYPES.DELETE_PYPERSDA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PYPERSDA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PYPERSDA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PYPERSDA):
    case SUCCESS(ACTION_TYPES.UPDATE_PYPERSDA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PYPERSDA):
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

const apiUrl = 'api/py-pers-das';

// Actions

export const getEntities: ICrudGetAllAction<IPyPersDa> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PYPERSDA_LIST,
  payload: axios.get<IPyPersDa>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPyPersDa> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PYPERSDA,
    payload: axios.get<IPyPersDa>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPyPersDa> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PYPERSDA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPyPersDa> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PYPERSDA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPyPersDa> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PYPERSDA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
