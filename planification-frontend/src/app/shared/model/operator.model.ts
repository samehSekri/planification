import { Unit } from "app/shared/model/unit.model";


export class Operator {
    constructor(
    public matricule: String,
    public firstname: String,
    public lastname: String,
    public email: String,
    public unite: Unit,
    public checked: boolean,
    public chargeHoraire: Number,
    public chargeHoraireParScenario: Number
  ) {
  }
}
