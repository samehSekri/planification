import { Unit } from "app/shared/model/unit.model";


export class Operator {
  /**
   * @param matricule 
   * @param firstname 
   * @param lastname 
   * @param email
   * @param ilot 
   * @param uap
   * @param atelier 
   * @param permissions
   */
  constructor(
  public matricule: Number,
  public firstname: string,
  public lastname: string,
  public email: string,
  public unite:Unit
  ) {
  }
}
