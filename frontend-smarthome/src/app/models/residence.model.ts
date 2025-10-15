import {User} from "./user.model";

export interface Residence {
  id?: number;
  street: string;
  houseNumber: string;
  zipCode: string;
  state: string;
  user?: User;
}
