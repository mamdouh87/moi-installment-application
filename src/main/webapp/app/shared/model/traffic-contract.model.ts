export interface ITrafficContract {
  id?: number;
  requestId?: number | null;
  requestNo?: string | null;
  requestServiceCode?: string | null;
  requestDescription?: string | null;
  plateNo?: string | null;
  motorNo?: string | null;
  chassisNo?: string | null;
  trafficUnitCode?: string | null;
}

export const defaultValue: Readonly<ITrafficContract> = {};
