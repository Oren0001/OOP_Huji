import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestClosedHashSet.class,
        TestOpenHashSet.class,
        TestDrive.class,
        TestClosedStudent2.class,
        TestOpenStudent2.class,
        TestStudent1.class
})

public class TestRunnerEx4 {
}
