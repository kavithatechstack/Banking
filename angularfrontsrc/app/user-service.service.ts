import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CUSTOMER_API_URL, httpHeaders } from './app.constants'
import { User } from './user/user';
import { catchError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpClient:HttpClient) { }

  viewListOfPlayers()
  {
    return this.httpClient.get(`${CUSTOMER_API_URL}/viewCustomers`)
  }
 addUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${CUSTOMER_API_URL}/saveCustomer`, user,{headers: httpHeaders});
      
}
deleteUser(user: User): Observable<string> {
  return this.httpClient.delete(`${CUSTOMER_API_URL}/deleteCust/${user.name}`, { responseType: 'text' })
}
}