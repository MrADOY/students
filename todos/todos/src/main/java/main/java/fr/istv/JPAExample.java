package main.java.fr.istv;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAExample {
	
	 private static EntityManager em = null;

		static {
			try {
				em = Persistence.createEntityManagerFactory("StudentService").createEntityManager();
			} catch (Throwable ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}
	  
   
    public static void main(String[] args) {
    	System.out.println("== Create students ==");
    	// Je demarre une transaction avec ma BDD
    	em.getTransaction().begin();
        Student student1 = createStudent("Sumith");
        Student student2 = createStudent("Anoop");
        // Je termine une transaction avec ma BDD
        em.getTransaction().commit();
        
        listStudent();
        
    	System.out.println("== Change name of students ==");
        em.getTransaction().begin();
        changeName(student1.getId(), "Sumith Honai");
        changeName(student2.getId(), "Anoop Pavanai");
        em.getTransaction().commit();
       
        listStudent();
     	
        System.out.println("== Delete student ==");
        em.getTransaction().begin();
        deleteStudent(student2.getId());
       	em.getTransaction().commit();
       	
        listStudent();

    }

    /**
     * 
     * @param studentName
     * @return Entity in database
     * Create a student in database
     */
	public static Student createStudent(String studentName) {
		Student student = new Student();
		student.setName(studentName);
		em.persist(student);
		return student;
	}
	
	/**
	 * 
	 * @param id
	 * @return Entity found in database
	 */
    public static Student findStudent(int id) {
        return em.find(Student.class, id);
    }

    /**
     * Print all entities in database
     */
	public static void listStudent() {
		List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class)
				.getResultList();
		students.forEach(System.out::println);
	}

	/**
	 * 
	 * @param studentId
	 * @param studentName
	 * Change name of student
	 */
	public static Student changeName(int id, String name) {
		Student student = (Student) em.find(Student.class, id);
		if (student != null) {
			student.setName(name);
		}
		return student;
	}

	/**
	 * 
	 * @param id
	 * Remove entity in database
	 */
	public static void deleteStudent(int id) {
		Student student = (Student) em.find(Student.class, id);
		em.remove(student);
	}
}
