package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(null);
        when(mockSocket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(mockSocket);

        verify(mockSocket, never()).setEnabledProtocols(any(String[].class));
    }

    @Test
    public void typical() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        doAnswer(invocation -> {
            String[] protocols = invocation.getArgument(0);
            String[] expected = {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"};
            assertArrayEquals(expected, protocols);
            return null;
        }).when(mockSocket).setEnabledProtocols(any());

        factory.prepareSocket(mockSocket);

    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

    //version amélioré

    @Test
    public void typicalV2() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockSocket = mock(SSLSocket.class);

        when(mockSocket.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(mockSocket.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        ArgumentCaptor<String[]> captor = ArgumentCaptor.forClass(String[].class);

        factory.prepareSocket(mockSocket);

        verify(mockSocket).setEnabledProtocols(captor.capture());
        String[] expectedProtocols = {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"};
        assertArrayEquals(expectedProtocols, captor.getValue());
    }
}