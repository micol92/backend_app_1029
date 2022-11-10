using my.bookshop as my from '../db/data-model';

service CatalogService @(requires: 'any') {
    entity Books as projection on my.Books ;
    entity POrders as projection on my.PurchaseOrders ;
    
    action SaveBook ();


    type BookTypes : {
        ID   : Integer;
        title  : String;
        stock  : Integer;
    }

    action SaveBookTypesEntityProc (Books : array of BookTypes) returns array of BookTypes;

    action SaveBookSingleTypesEntity (ID : Integer, title : String, stock : Integer) returns BookTypes;


}
//annotate CatalogService.Books with @odata.draft.enabled;