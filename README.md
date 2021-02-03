# ProjectManagement


Not finished yet.


@ControllerAdvice : Spring MVC uygulamalarında küresel hata işleme için kullanılır. Ayrıca response'un body ve http status kodu üzerinde tam denetime sahiptir.

@PrePersist : Entity kayıt edilmeden önce çalıştırılacak metodu ifade eder.

@PreUpdate : Entity güncellenmeden önce çalıştırılacak metodu ifade eder.

@JsonIgnore : JSON verisinde görünmesi istenmeyen fieldlar özel olarak belirtmek istenirse kullanılır.


------------------ 

## FetchType : 

Aralarında ilişki bulunan entitylerden bir tarafı yüklerken diğer tarafın yüklenme stratejisini belirlememize olanak sağlar.

Hibernate içerisinde EAGER ve LAZY şeklinde 2 tip entity yükleme stratejisi vardır. Bu tipleri örnekle açıklayacak olursak;

Elimizde proje Backlog ve bu projelerin Task'larını tuttuğumuz iki entity var. Backlog ve ProjectTask arasında bir ilişki bulunduğundan veritabanında, Backlog entitysini yüklediğimizde ilişkili olduğu ProjectTask tablosununda yüklenmesini istiyorsak FetchType tipini fetch=FetchType.EAGER olarak belirledik.

Eğer Backlog entitysini yüklediğimizde ProjectTask entitysinin yüklenmesini istemeseydik, yani ihtiyaç olması halinde ProjectTask entitysini yükleseydik FetchType tipini fetch=FetchType.LAZY olarak belirlerdik. 


### Uygulama içerisinde nasıl belirlemeliyiz ?

Eğer @OneToOne veya @ManyToOne tipinde ilişki bulunan veritabanınlarından yararlanıyorsak FetchType olarak EAGER(Ön Yükleme) kullanmak daha doğrudur. Yani ilişkili entity bir tane olduğundan ön yükleme yapmak performans açısından bir sorun oluşturmaz.

-----------------------------

## Cascade 

Cascade, bir JPA standardıdır. Entity sınıflarımızdaki ilişkilerin hareketlerini yani davranışlarını cascade tipleri ile ayarlarız.

* Persist	Nesne persist edilirse ilişkili nesnelerde persist edilir
* All	Tüm işlemleri ilişkili nesnelerle birlikte yapar
* Merge	Nesne merge edilirse ilişkili nesnelerde merge edilir
* Remove	Nesne remove edilirse ilişkili nesnelerde remove edilir
* Refresh	Nesne refresh edilirse ilişkili nesnelerde refresh edilir

Eğer ki  @OneToMany veya  @ManyToMany kullanıyorsak da FetchType olarak LAZY kullanmak daha doğrudur. Çünkü ilişkili entityler çok sayıda olması halinde ön yükleme yapacak olursak bu durum performans kaybına neden olur. Bunun için ihtiyaç olması halinde yüklemek daha doğru bir çözüm.

---------------

* Eğer cascade={"remove"} veya orphanRemoval=true özelliğinin ayarlı olduğu entityden bir kayıt silerseniz, bu işlem diğer ilişkisel entitylerden de otomatik olarak kayıtları silmeyi deneyecektir.

