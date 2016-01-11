package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "findAllPerson", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "findPersonById", query = "SELECT p FROM Person p WHERE p.id = :PersonId"),
    @NamedQuery(name = "findPersonByName", query = "SELECT p FROM Person p WHERE p.name = :PersonName"),
    @NamedQuery(name = "findPersonFriends", query = "SELECT p FROM Person p Join p.friends f WHERE f.id = :id")
})
public class Person {

	private Long id;
	private String name;
	private String firstName;
	
	private String mail;
	private List<Home> homes;
	private List<ElectronicDevice> eds;
	private List<Person> friends;
	
	public Person() {
		super();
		this.homes = new ArrayList<Home>();
		this.friends = new ArrayList<Person>();
	}

	public Person(String name, String firstName, String mail, List<Home> homes) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.mail = mail;
		this.homes = homes;
	}
	
	@Id
    @GeneratedValue
	@Column(name="PERSON_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@OneToMany(mappedBy="inhabitant")
	public List<Home> getHomes() {
		return homes;
	}


	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}


	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@OneToMany(mappedBy="person")
	public List<ElectronicDevice> getEds() {
		return eds;
	}

	public void setEds(List<ElectronicDevice> eds) {
		this.eds = eds;
	}

    @JoinTable(name = "Friends", joinColumns = {
            @JoinColumn(name = "Friend_Id", referencedColumnName = "PERSON_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "Friend_Id1", referencedColumnName = "PERSON_ID")})
     @ManyToMany
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	public void addFriends(Person p){
		this.friends.add(p);
		p.friends.add(this);
	}

	@Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", first name=" + firstName + "]";
    }

	
}