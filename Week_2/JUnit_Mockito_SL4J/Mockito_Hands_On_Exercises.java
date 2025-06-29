// Combined Mockito Exercises
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// External API interface to be mocked
interface ExternalApi {
    String getData();
    void sendData(String data);
    String fetch(String key);
    void clearCache();
}

// Service that uses the ExternalApi
class MyService {
    private ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public void sendData(String data) {
        api.sendData(data);
    }

    public String fetchByKey(String key) {
        return api.fetch(key);
    }

    public void reset() {
        api.clearCache();
    }
}

// ==========================================
// Exercise 1: Mocking and Stubbing
// ==========================================
class MyServiceMockingTest {

    @Test
    public void testExternalApi() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }
}

// ==========================================
// Exercise 2: Verifying Interactions
// ==========================================
class MyServiceVerifyTest {

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData(); // Verifies the method was called
    }
}

// ==========================================
// Exercise 3: Argument Matching
// ==========================================
class MyServiceArgumentTest {

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.sendData("Hello");

        verify(mockApi).sendData(eq("Hello")); // Match specific argument
    }
}

// ==========================================
// Exercise 4: Handling Void Methods
// ==========================================
class MyServiceVoidMethodTest {

    @Test
    public void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doNothing().when(mockApi).sendData(anyString());

        MyService service = new MyService(mockApi);
        service.sendData("Testing");

        verify(mockApi).sendData("Testing");
    }
}

// ==========================================
// Exercise 5: Mocking with Multiple Returns
// ==========================================
class MyServiceMultipleReturnTest {

    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("First Call", "Second Call", "Third Call");

        MyService service = new MyService(mockApi);

        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call", service.fetchData());
    }
}

// ==========================================
// Exercise 6: Verifying Interaction Order
// ==========================================
class MyServiceOrderTest {

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        mockApi.sendData("first");
        mockApi.getData();
        mockApi.clearCache();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).sendData("first");
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).clearCache();
    }
}

// ==========================================
// Exercise 7: Void Methods with Exceptions
// ==========================================
class MyServiceExceptionTest {

    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        doThrow(new RuntimeException("Clearing error")).when(mockApi).clearCache();

        MyService service = new MyService(mockApi);

        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() {
                service.reset();
            }
        });

        verify(mockApi).clearCache();
    }
}
