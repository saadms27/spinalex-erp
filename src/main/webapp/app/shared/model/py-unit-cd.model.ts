import { IPyPersDa } from 'app/shared/model//py-pers-da.model';

export interface IPyUnitCd {
  id?: number;
  uCd?: number;
  uNm?: string;
  uCd?: IPyPersDa;
}

export const defaultValue: Readonly<IPyUnitCd> = {};
