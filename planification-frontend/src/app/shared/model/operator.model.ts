

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
  public matricule: number,
  public username: string,
  public firstname: string,
  public lastname: string,
  public email: string,
  public uap: String,
  public atelier: String,
  public ilot: String,
  ) {
  }
}
