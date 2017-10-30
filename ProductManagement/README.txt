This is the Product Management module of the MyStore application.
You can build this separately using the attached Gradle script.

You can find the list of available APIs and its usage in the table below. For additional info, please refer to Javadoc.



+==========================+=====================================================+============+==================+==================+
|     Method               |       API Signature ==> http://{host}:8080/api/v1/prdmgt         |    Accepts       |    Returns       |
+==========================+==================================================================+==================+==================+
| GET: Product By ID       | /prd/{id}                                                        | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| GET: Product List        | /prd/list/{pageNo}/{pageSize}                                    | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| GET: Product List        | /prd/list/{pageNo}                                               | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| GET: Product List        | /prd/list                                                        | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| GET: Product List        | /prd/listByCategory/{categoryId}/{pageNo}/{pageSize}             | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| POST: Create Product     | /prd/create                                                      | application/json | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| PUT: Update Product      | /prd/update                                                      | application/json | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| PUT: Delete Category     | /cat/delete/{id}                                                 | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| GET: Category By ID      | /cat/{id}                                                        | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| GET: Category List       | /cat/list/{pageSize}/{pageNo}                                    | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| POST: Create Category    | /cat/create                                                      | application/json | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| PUT: UpdateCategory      | /cat/update                                                      | application/json | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+
| PUT: Delete Category     | /cat/delete/{id}                                                 | --               | application/json |
+--------------------------+------------------------------------------------------------------+------------------+------------------+




#### Before testing the APIs via POSTMAN, please run the DDL attached in the project. ####

Sample API #1:

GET: http://localhost:8080/api/v1/prdmgt/prd/1

Return:
{
    "id": 1,
    "code": "BC-123456",
    "name": "Beuchat Maxlux Scuba Mask",
    "createTs": 1508934825259,
    "createBy": "dba",
    "updateTs": 1508934825259,
    "updateBy": "dba",
    "productDetail": {
        "id": null,
        "productId": null,
        "availableStocks": "Item stocks not yet available.",
        "price": "Item price not yet available."
    },
    "categories": [
        {
            "id": 1,
            "code": "REGULAR",
            "name": "REGULAR",
            "createTs": null,
            "createBy": null,
            "updateTs": null,
            "updateBy": null
        },
        {
            "id": 3,
            "code": "MASK",
            "name": "MASK",
            "createTs": null,
            "createBy": null,
            "updateTs": null,
            "updateBy": null
        }
    ]
}



Sample API #2:

POST: http://localhost:8080/api/v1/prdmgt/prd/create

PAYLOAD:
{
	"id":5,
	"code":"TS-123456",
	"name":"test",
	"createBy":"postman",
	"updateBy":"postman"
}

RETURN:
{
    "id": 5,
    "code": "TS-123456",
    "name": "test",
    "createTs": null,
    "createBy": "test",
    "updateTs": null,
    "updateBy": "test",
    "productDetail": {
        "id": null,
        "productId": null,
        "availableStocks": "Item stocks not yet available.",
        "price": "Item price not yet available."
    },
    "categories": []
}

