import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../types/Category.interface';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private readonly baseAPIURL = environment.baseAPIURL

  constructor(private httpClient: HttpClient) { }

  getAllCategories(): Observable<Category[]>{
    	
    const APIURL = `${this.baseAPIURL}/categories`;

    return this.httpClient.get<Category[]>(APIURL);
  }

  filterCategoryById(listCategory: Category[], categoryId: string) {

    for (let category of listCategory){
      if(category.id === Number(categoryId)){
        return category.listProducts; // The products receive the category data reference a category
      }
    }
    return [];
  }
}
