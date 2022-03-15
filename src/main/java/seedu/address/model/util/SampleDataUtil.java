package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.util.Pair;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Telegram;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getTagSet("friends"),
                new GithubUsername("alexyeoh"), new Telegram("alex_yeoh"), new StudentId("A0123456B")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), getTagSet("colleagues", "friends"),
                new GithubUsername("berniceyu"), new Telegram("bernice_yu"), new StudentId("A1234567C")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), getTagSet("neighbours"),
                new GithubUsername("charlotteoliveiro"), new Telegram("charlotte_oliverio"),
                new StudentId("A0123457C")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), getTagSet("family"),
                new GithubUsername("davidli"), new Telegram("david_li"), new StudentId("A0123458D")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), getTagSet("classmates"),
                new GithubUsername("irfanibrahim"), new Telegram("irfan_ibrahim"), new StudentId("A0123459E")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), getTagSet("colleagues"),
                new GithubUsername("roybalakrishnan"), new Telegram("roy_balakrishnan"),
                new StudentId("A0123450F"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a LabList containing the list of strings given.
     */
    @SafeVarargs
    public static LabList getLabSet(Pair<String, String>... strings) {
        LabList ll = new LabList();

        ll.setLabs(Arrays.stream(strings)
                .map(x -> (new Lab(x.getKey())).of(x.getValue()))
                .collect(Collectors.toList()));
        return ll;
    }

}
