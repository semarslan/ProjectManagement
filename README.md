# ProjectManagement


Not finished yet.


@ControllerAdvice : Spring MVC uygulamalarında küresel hata işleme için kullanılır. Ayrıca response'un body ve http status kodu üzerinde tam denetime sahiptir.

@PrePersist : Entity kayıt edilmeden önce çalıştırılacak metodu ifade eder.

@PreUpdate : Entity güncellenmeden önce çalıştırılacak metodu ifade eder.

@JsonIgnore : JSON verisinde görünmesi istenmeyen fieldlar özel olarak belirtmek istenirse kullanılır.


------------------ 

## FetchType : 

Aralarında ilişki bulunan entitylerden bir tarafı yüklerken diğer tarafın yüklenme stratejisini belirlememize olanak sağlar.

Hibernate içerisinde EAGER(Ön Yükleme) ve LAZY(Tembel/Sonradan Yükleme) şeklinde 2 tip entity yükleme stratejisi vardır. Bu tipleri örnekle açıklayacak olursak;

Elimizde yürütülen projeleri (Proje) ve bu projelerde çalışanları(Calisan) tuttuğumuz iki entity olsun. Projeler ve çalışanlar arasında bir ilişki bulunduğundan veritabanından Proje entitysini yüklediğimizde ilişkili olduğu Calisan tablosununda yüklenmesini istiyorsak FetchType tipini fetch=FetchType.EAGER olarak belirleriz.

Bunun aksine Proje entitysini yüklediğimizde Calisan entitysinin yüklenmesini istemiyorsak yani ihtiyaç olması halinde Calisan entitysini yüklemek istiyorsak FetchType tipini fetch=FetchType.LAZY olarak belirleriz. 


### Peki geliştireceğimiz uygulama içerisinde bu tiplerinin kullanımını nasıl belirlemeliyiz ?

Eğer @OneToOne veya @ManyToOne tipinde ilişki bulunan veritabanınlarından yararlanıyorsak FetchType olarak EAGER(Ön Yükleme) kullanmak daha doğrudur. Yani ilişkili entity bir tane olduğundan ön yükleme yapmak performans açısından bir sorun oluşturmaz.

Eğer ki  @OneToMany veya  @ManyToMany kullanıyorsak da FetchType olarak LAZY(Tembel Yükleme) kullanmak daha doğrudur. Çünkü ilişkili entityler çok sayıda olması halinde ön yükleme yapacak olursak bu durum performans kaybına neden olur. Bunun için ihtiyaç olması halinde yüklemek daha doğru bir çözüm olur.

-----------------------------

## Cascade 

Cascade, bir JPA standardıdır. Entity sınıflarımızdaki ilişkilerin hareketlerini yani davranışlarını cascade tipleri ile ayarlarız.

* Persist	Nesne persist edilirse ilişkili nesnelerde persist edilir
* All	Tüm işlemleri ilişkili nesnelerle birlikte yapar
* Merge	Nesne merge edilirse ilişkili nesnelerde merge edilir
* Remove	Nesne remove edilirse ilişkili nesnelerde remove edilir
* Refresh	Nesne refresh edilirse ilişkili nesnelerde refresh edilir
