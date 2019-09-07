# CRUD + JDBC + Hibernate

   1) прочитать про паттерны Синглтон и Абстрактная фабриика
   2) у UserDao две реализации UserDaoJDBCimpl и UserDaoHibernateImpl
   3) UserDaoFactory - абстрактная фабрика которая возвращает реализацию дао     на основе файла property
   4) DBHelper - синглтон, у него есть два метода getConnection и getConfiguration которые отдают Connection для jdbc dao и Configuration для hibernatedao соотвтетственно
   5) Сервис - синглтон
