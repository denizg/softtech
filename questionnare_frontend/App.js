import React from 'react';
import {ActivityIndicator, SafeAreaView, SectionList, StyleSheet, Text, View, Alert} from 'react-native';
import Constants from 'expo-constants';
import RNPickerSelect from 'react-native-picker-select';
import Button from 'react-native-web/dist/exports/Button';

const getPollFromServer = () => {
    return fetch('http://localhost:8080/questionnare/rest/poll')
        .then(response => {
            return response.json();
        })
        .catch((error) => {
            console.log(error);
        });
};

const postAnswers = (state) => {
    const answerValues = [];
    for (const key in state.answers) {
        answerValues.push(state.answers[key]);
    }
    const data = JSON.stringify( answerValues ) ;
    console.log("deni "+ data )
    fetch('http://localhost:8080/questionnare/rest/answers',
        {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: data
        })
        .then(response => {
            window.location.reload(true);
        })
        .catch((error) => {
            console.log(error);
        });
};

export default class App extends React.Component {
    constructor(props) {
        super(props);
    }

    state = {questions: [], answers: {}};

    componentDidMount() {
        this._asyncRequest = getPollFromServer().then(
            data => {
                this._asyncRequest = null;
                this.setState({questions: data});
            },
        );
    }

    componentWillUnmount() {
        if (this._asyncRequest) {
            this._asyncRequest.cancel();
        }
    }

    render() {
        if (this.state.questions === null) {
            return (
                <View>
                    <ActivityIndicator size="large"/>
                </View>
            );
        } else {
            return (
                <SafeAreaView style={styles.container}>
                    <View>
                        <Text style={styles.text}> Please fill the personnel questionnaire below.</Text>
                        <SectionList
                            sections={this.state.questions}
                            keyExtractor={(item, index) => item + index}
                            renderItem={({item}) =>
                                <RNPickerSelect
                                    placeholder={item}
                                    onValueChange={(value) => (
                                        this.setState(state => {
                                            // TODO if I had a chance to implement number range I would be sending in "selection" attribute instead of option id, number value
                                            state.answers[item.value] = {"questionType":item.questionType, "questionId":item.value, "selection":value} ;
                                        })
                                    )}
                                    items={item.options}
                                />
                            }
                            renderSectionHeader={({section: {title}}) => (
                                <Text style={styles.header}>{title}</Text>
                            )}
                        />
                    </View>
                    <View style={styles.pollEndButton}>
                        <Text style={styles.title}/>
                        <Button
                            name='submit'
                            onPress={() => {
                                postAnswers(this.state);
                                alert(
                                    "Submitting your answers."
                                );
                            }}
                            title="Submit">
                        </Button>
                    </View>
                </SafeAreaView>
            );
        }
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: Constants.statusBarHeight,
        marginHorizontal: 16,
    },
    pollEndButton: {
        alignItems: 'flex-end',
    },
    header: {
        fontSize: 32,
        backgroundColor: '#fff',
        marginVertical: 10,
    },
    title: {
        textAlign: 'center',
        marginVertical: 8,
    },
    text: {
        fontSize: 30,
        alignSelf: 'center',
        color: 'red',
    },
});


