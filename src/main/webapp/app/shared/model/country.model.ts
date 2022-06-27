export interface ICountry {
  id?: number;
  code?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
}

export const defaultValue: Readonly<ICountry> = {};
