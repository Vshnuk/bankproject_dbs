package com.bank.errors.repository;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;

	import com.bank.errors.model.Transactions;


	public interface TransactionDaoRepository extends JpaRepository<Transactions,Integer> {
		@Query(value= " select * from transactions where from_acc=:givenacnum and UNIX_TIMESTAMP(trans_date)>=UNIX_TIMESTAMP(:date1) and UNIX_TIMESTAMP(trans_date)<=UNIX_TIMESTAMP(:date2) order by trans_date desc limit 10;",nativeQuery = true)
		List<Transactions> getAllTransactions(@Param("givenacnum") int from_account,@Param("date1") String date1, @Param("date2") String date2);
		@Query(value= "select sum(amount) from transactions where from_acc=:accno and type='Debited From' and UNIX_TIMESTAMP(trans_date)>=UNIX_TIMESTAMP(:date1) and UNIX_TIMESTAMP(trans_date)<=UNIX_TIMESTAMP(:date2)",nativeQuery = true)
		Long getSumOfBalance(@Param("accno") int accno, @Param("date1") String date1, @Param("date2") String date2);
	}
