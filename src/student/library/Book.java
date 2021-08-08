package student.library;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock;

	public Book(int id) {
		this.lock = new ReentrantLock();
		this.id = id;
	}

	public void read(Student student) throws InterruptedException {

		if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
			System.out.println(student + " starts reading " + this);
			Thread.sleep(3000);
			lock.unlock();
			System.out.println(student + " has finished reading " + this);
		} else {
			System.out.println(student + " this book is not available now....");
			Thread.sleep(1000);
		}

	}

	public String toString() {
		return "Book" + id;
	}

}