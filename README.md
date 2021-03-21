# ProjectManagement


# Tech Stack
* Java 8
* Spring Boot
* Spring Security
* Lombok
* PostgreSQL
* React 
* Redux

# Json

## User
* http://localhost:8080/api/users/register
{
    "username" : "94.sema.arslan@gmail.com",
    "fullName" :"sema arslan",
    "password" : "semasema",
    "confirmPassword" : "semasema"
}

* http://localhost:8080/api/users/login
{
    "password": "semasema",
    "username": "94.sema.arslan@gmail.com"
}

##Project Manager
* Create Project: http://localhost:8080/api/project
{
    "projectName" : "test project",
    "projectIdentifier" : "ID01",
    "description": "test description"
}

* Update Project: http://localhost:8080/api/project
{
    "id" : 1,
    "projectName" : "test project update",
    "projectIdentifier" : "ID01",
    "description": "test description"
}

* Get Project: http://localhost:8080/api/project/ID01

* Get All Projects: http://localhost:8080/api/project/all

* Delete Project: http://localhost:8080/api/project/ID01

## Task Project 

* Create Backlog: http://localhost:8080/api/backlog/ID01

{
    "summary" : "Test"
}

* Get Project Task: http://localhost:8080/api/backlog/ID01

* Project Sequence: http://localhost:8080/api/backlog/ID01/ID01-1

* Update Project Task: http://localhost:8080/api/backlog/ID01/ID01-1
{
    "id": 1,
    "projectSequence": "ID01-1",
    "summary": "Update Test",
    "acceptanceCriteria": null,
    "status": "TO_DO",
    "priority": 3,
    "dueDate": null,
    "createdAt": "2021-02-02T12:33:23.789+0000",
    "updatedAt": null,
    "projectIdentifier": "ID01"
}

* Delete Project Task: http://localhost:8080/api/backlog/ID01/ID01-1
------------------

@ControllerAdvice : Spring MVC uygulamalarında küresel hata işleme için kullanılır. Ayrıca response'un body ve http status kodu üzerinde tam denetime sahiptir.

@PrePersist : Entity kayıt edilmeden önce çalıştırılacak metodu ifade eder.

@PreUpdate : Entity güncellenmeden önce çalıştırılacak metodu ifade eder.

@JsonIgnore : JSON verisinde görünmesi istenmeyen fieldlar özel olarak belirtmek istenirse kullanılır.


------------------ 

## FetchType  

Aralarında ilişki bulunan entitylerden bir tarafı yüklerken diğer tarafın yüklenme stratejisini belirlememize olanak sağlar.

Hibernate içerisinde EAGER ve LAZY şeklinde 2 tip entity yükleme stratejisi vardır. Bu tipleri örnekle açıklayacak olursak;

Elimizde proje Backlog ve bu projelerin Task'larını tuttuğumuz iki entity var. Backlog ve ProjectTask arasında bir ilişki bulunduğundan veritabanında, Backlog entitysini yüklediğimizde ilişkili olduğu ProjectTask tablosununda yüklenmesini istiyoruz ve bu nedenle FetchType tipini fetch=FetchType.EAGER olarak belirledik.

Eğer Backlog entitysini yüklediğimizde ProjectTask entitysinin yüklenmesini istemeseydik, yani ihtiyaç olması halinde ProjectTask entitysini yükleseydik FetchType tipini fetch=FetchType.LAZY olarak belirlerdik. 


### Uygulama içerisinde nasıl belirlemeliyiz ?

Eğer @OneToOne veya @ManyToOne tipinde ilişki bulunan veritabanınlarından yararlanıyorsak FetchType olarak EAGER(Ön Yükleme) kullanmak daha doğrudur. Yani ilişkili entity bir tane olduğundan ön yükleme yapmak performans açısından bir sorun oluşturmaz.

-----------------------------

## Cascade 

Cascade, bir JPA standardıdır. Entity sınıflarımızdaki ilişkilerin hareketlerini yani davranışlarını cascade tipleri ile ayarlarız.

* Persist- Nesne persist edilirse ilişkili nesnelerde persist edilir
* All	Tüm- işlemleri ilişkili nesnelerle birlikte yapar
* Merge-	Nesne merge edilirse ilişkili nesnelerde merge edilir
* Remove-	Nesne remove edilirse ilişkili nesnelerde remove edilir
* Refresh-	Nesne refresh edilirse ilişkili nesnelerde refresh edilir

Eğer ki  @OneToMany veya  @ManyToMany kullanıyorsak da FetchType olarak LAZY kullanmak daha doğrudur. Çünkü ilişkili entityler çok sayıda olması halinde ön yükleme yapacak olursak bu durum performans kaybına neden olur. Bunun için ihtiyaç olması halinde yüklemek daha doğru bir çözüm.

---------------

* Eğer cascade={"remove"} veya orphanRemoval=true özelliğinin ayarlı olduğu entityden bir kayıt silerseniz, bu işlem diğer ilişkisel entitylerden de otomatik olarak kayıtları silmeyi deneyecektir.

--------------

## Put vs Patch 

Bu iki metot birbirine benzese de patch birden fazla kısımdan oluşan bir kaynağın bir parçasını dönerken tercih edilmelidir. 
Put ise kaynağın tamamını dönülecekse tercih edilmelidir. 
* Put metodu yapısı gereği hantaldır, küçük update işlemlerinde patch daha verimli çalışacaktır.
