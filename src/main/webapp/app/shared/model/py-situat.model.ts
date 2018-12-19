import { IPyPersDa } from 'app/shared/model//py-pers-da.model';

export interface IPySituat {
  id?: number;
  sitCd?: number;
  sitNm?: string;
  sitCd?: IPyPersDa;
}

export const defaultValue: Readonly<IPySituat> = {};
