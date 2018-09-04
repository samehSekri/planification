import { Unit } from "app/shared/model/unit.model";


export class Operator {
    constructor(
    public matricule: String,
    public firstname: string,
    public lastname: string,
    public email: string,
    public unite: Unit,
    public checked: boolean
  ) {
  }
}
