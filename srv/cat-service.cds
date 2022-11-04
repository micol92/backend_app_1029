using my.bookshop as my from '../db/data-model';

service CatalogService @(requires: 'any') {
    entity Books as projection on my.Books ;
    
    action SaveBook ();


    type BookTypes : {
        ID : Integer;
        title  : String;
        stock  : Integer;
    }

    action SaveBookTypesEntityProc (inputData : BookTypes) returns Integer;


}
//annotate CatalogService.Books with @odata.draft.enabled;