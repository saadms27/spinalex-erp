import { IPyUnitCd } from 'app/shared/model//py-unit-cd.model';
import { IPySituat } from 'app/shared/model//py-situat.model';

export interface IPyPersDa {
  id?: number;
  empNo?: string;
  uUCd?: number;
  sitCd?: number;
  uUCd?: IPyUnitCd;
  sitCd?: IPySituat;
}

export const defaultValue: Readonly<IPyPersDa> = {};
