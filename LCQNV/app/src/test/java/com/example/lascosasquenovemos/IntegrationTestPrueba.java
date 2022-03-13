package com.example.lascosasquenovemos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
public class IntegrationTestPrueba {

    private DatabaseReference mockedDatabaseReference;
    private  PruebaEscribirBD PEBD;
    private PruebaLeerBD PLBD;

    @Before
    public void setupTests(){
        try{
            mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

            FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
            when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);

            PowerMockito.mockStatic(FirebaseDatabase.class);
            when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
        } catch(Exception e){
            if(e.getCause().getClass().equals(AssertionError.class)){
                // handle your exception  1
            } else {
                // handle the rest of the world exception
            }
        }


    }

    @Test
    public void escribirBD(){

        String var = anyString();

        when(mockedDatabaseReference.child(var)).thenReturn(mockedDatabaseReference);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];

                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)

                valueEventListener.onDataChange(mockedDataSnapshot);
                //valueEventListener.onCancelled(...);

                return null;
            }
        }).when(mockedDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));

        new PruebaLeerBD().leer(var);
    }
}
